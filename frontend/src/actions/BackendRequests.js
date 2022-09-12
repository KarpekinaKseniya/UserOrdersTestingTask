export const findAllUsers = () => {
    return fetch('/v1/users')
        .then(response => response.json())
        .catch(error => alert(error));
}

export const findAllOrders = () => {
    return fetch('/v1/orders')
        .then(response => response.json())
        .catch(error => alert(error));
}

export const findAllCompanies = () => {
    return fetch('/v1/companies')
        .then(response => response.json())
        .catch(error => alert(error))
}