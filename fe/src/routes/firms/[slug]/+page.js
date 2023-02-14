/**
 * Glue for picking up the slug from the route.
 *
 * @param params
 * @returns {{slug: *}}
 */

export const load = ({ params }) => {
    return {
        slug: params.slug
    }
}

// EOF
