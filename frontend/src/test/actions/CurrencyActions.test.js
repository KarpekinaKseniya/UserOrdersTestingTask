import {countNewValue, getAllCurrency} from "../../actions/CurrencyActions";

const CURRENCY_MOCK = {
    "result": "success",
    "base_code": "USD",
    "rates": {
        "USD": 1,
        "AED": 3.6725,
        "BYN": 2.731437,
        "BZD": 2,
        "CZK": 24.577553
    }
}

test('should convert to CZK from USD', () => {
    const actual = countNewValue(CURRENCY_MOCK, 'CZK', 'USD', 30)
    expect(actual).toBe(737.32659)
})

const unMockedFetch = global.fetch

afterAll(() => {
    global.fetch = unMockedFetch
})

describe('should return rates', () => {

    beforeEach(() => {
        global.fetch = () =>
            Promise.resolve({
                json: () => Promise.resolve(CURRENCY_MOCK),
            })
    })

    test('Is it working?', async () => {
        const currency = await getAllCurrency()
        expect(currency).toEqual(CURRENCY_MOCK)
    })
})

describe('should return error', () => {

    beforeEach(() => {
        global.fetch = () =>
            Promise.resolve({
                json: () => Promise.reject(),
            })
    })

    test('Is it working?', async () => {
        const alertMock = jest.spyOn(window, 'alert').mockImplementation()
        try {
            await getAllCurrency()
        } finally {
            expect(alertMock).toHaveBeenCalledTimes(1)
        }
    })
})