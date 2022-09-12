import {findAllCompanies, findAllOrders, findAllUsers} from "../../actions/BackendRequests";

const USERS = [
    {
        "id": 4,
        "first_name": "Torrie"
    },
    {
        "id": 19,
        "first_name": "Brigitte"
    }
]

const ORDERS = [
    {
        "id": 21,
        "total": 1073.39
    },
    {
        "id": 149,
        "total": 112.15
    }
]

const COMPANIES = [
    {
        "id": 7,
        "title": "McGlynn LLC"
    },
    {
        "id": 4,
        "title": "Ondricka Inc"
    }
]

const UN_MOCKED_FETCH = global.fetch

afterAll(() => {
    global.fetch = UN_MOCKED_FETCH
})

describe('should return all users', () => {

    beforeEach(() => {
        global.fetch = () =>
            Promise.resolve({
                json: () => Promise.resolve(USERS),
            })
    })

    test('Is it working?', async () => {
        const users = await findAllUsers()
        expect(users).toEqual(USERS)
    })
})

describe('should return all orders', () => {

    beforeEach(() => {
        global.fetch = () =>
            Promise.resolve({
                json: () => Promise.resolve(ORDERS),
            })
    })

    test('Is it working?', async () => {
        const orders = await findAllOrders()
        expect(orders).toEqual(ORDERS)
    })
})

describe('should return all companies', () => {

    beforeEach(() => {
        global.fetch = () =>
            Promise.resolve({
                json: () => Promise.resolve(COMPANIES),
            })
    })

    test('Is it working?', async () => {
        const companies = await findAllCompanies()
        expect(companies).toEqual(COMPANIES)
    })
})

describe('should return error', () => {

    beforeEach(() => {
        global.fetch = () =>
            Promise.resolve({
                json: () => Promise.reject(),
            })
    })

    test('get all users', async () => {
        const alertMock = jest.spyOn(window, 'alert').mockImplementation()
        try {
            await findAllUsers()
        } finally {
            expect(alertMock).toHaveBeenCalledTimes(1)
        }
    })

    test('get all orders', async () => {
        const alertMock = jest.spyOn(window, 'alert').mockImplementation()
        try {
            await findAllOrders()
        } finally {
            expect(alertMock).toHaveBeenCalledTimes(1)
        }
    })

    test('get all companies', async () => {
        const alertMock = jest.spyOn(window, 'alert').mockImplementation()
        try {
            await findAllCompanies()
        } finally {
            expect(alertMock).toHaveBeenCalledTimes(1)
        }
    })
})