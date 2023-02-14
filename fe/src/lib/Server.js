import _ from 'lodash';
import {objGet} from "./Utils";

class Server {
    serverUrl = "http://localhost:8080/server";

    getServerUrl () {
        return this.serverUrl;
    }

    setServerUrl (url) {
        this.serverUrl = url;
        return;
    }

    /**
     * Creates a normal application/json GraphQL request.
     *
     * @param query
     * @param variables
     * @param serverUrl
     * @returns {Request}
     */

    graphQLRequest (query, variables, serverUrl) {
        if (! variables) {
            variables = {};
        }
        if (_.isString (variables)) {
            variables = JSON.parse (variables);
        }

        const headers = {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        };
        if (! serverUrl) {
            headers['Csrf-Token'] = this.csrfToken;
        }

        // Send the request

        const url = this.getServerUrl();
        return new Request(url, {
            method: 'POST',
            mode: 'cors',
            redirect: 'follow',
            credentials: 'include',
            headers: new Headers(headers),
            body: JSON.stringify({
                query,
                variables
            })
        });
    }

    /**
     * Removes any variables that are null since the most recent update to graphql-java
     * doesn't seem to allow this case. Annoying.
     *
     * @param vars
     * @returns {*}
     */

    scrubVariables (vars) {
        if(vars) {
            _.each(vars,(value, key) => {
                if(value === null) {
                    delete vars[key]
                }
            });
        }
        return vars;
    }

    async gql (query, variables, files, headers) {
        // const server = this.settings.server;
        variables = this.scrubVariables (variables);
        const request = this.graphQLRequest (query, variables, headers);
        const res = await fetch (request);
        return await res.json();
    }

    async _gql (gql, vars, files, headers) {
        const op = await this.gql (gql, vars, files, headers);
        const res = objGet (op, [ "data", "res" ]);
        if(! _.isNil (res)) {
            return res;
        } else {
            throw op;
        }
    }

}

export default new Server ();

// EOF