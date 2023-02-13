import Server from "$lib/Server.js";
import _ from "lodash";


class SECReport {
    byStateGql = "query { res: secReportByState }";

    async getByState () {
        const res = await Server._gql (this.byStateGql);
        const arr = [];
        _.each (res, (val, key) => {
            arr.push ([key, val])
        });
        arr.sort ((a, b) => b[1] - a[1]);
        return arr;
    }

    getSummaryGql = "query { res: secReport { count generatedOn } }";

    async getSummary () {
        return await Server._gql (this.getSummaryGql);
    }
}

export default new SECReport ();

// EOF