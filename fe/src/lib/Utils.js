import _ from 'lodash';

/**
 *
 * @param obj
 * @param path
 * @returns {undefined|*}
 */

const clamp = (value, min, max) => {
    if (value < min) {
        return min;
    } else if (value > max) {
        return max;
    } else {
        return value;
    }
};

const objGet = (obj, path) => {
    if (! _.isArray (path)) {
        path = path.split(".");
    }
    for (let i = 0; i < path.length; i ++) {
        if (! obj) {
            return undefined;
        }
        obj = obj[path[i]];
    }
    return obj;
};

const withCommas = (x) => {
    if (x !== null && x !== undefined) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    } else {
        return "-";
    }
};

const maybeError = (e) => {
    if (e === null) {
        return null;
    } else if (_.isObject(e)) {
        return e.errors[0].message;
    } else {
        return e.toString();
    }
};

const isJson = (json) => {
    try {
        JSON.parse(json);
        return true;
    } catch (e) {
        return false;
    }
};

const maybeNull = (el) => el || '-';

export {
    objGet,
    maybeNull,
    withCommas,
    clamp,
    isJson,
    maybeError
};

// EOF