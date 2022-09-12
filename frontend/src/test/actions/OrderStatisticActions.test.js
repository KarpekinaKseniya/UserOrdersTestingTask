import {ordersAverageValue, ordersMedianValue, ordersTotal} from "../../actions/OrdersStatisticActions";

const ORDERS = [
    {
        id: 1,
        created_at: "1543325996",
        total: 531.57,
    },
    {
        id: 2,
        created_at: "1425057783",
        total: 593.72,
    },
    {
        id: 3,
        created_at: "1481134813",
        total: 971.59,
    },
]

test('should count orders total', () => {
    const actual = ordersTotal(ORDERS)
    expect(actual).toBe(2096.88)
})

describe('Count orders median', () => {
    it.each`
    orders | result
    ${ORDERS} | ${593.72}
    ${[{id: 2, created_at: "1425057783", total: 593.72,}, {id: 4, created_at: "1435634501", total: 722.30}]} | ${658.01}
    `
    ('Expecting $result when count orders median with odd/even numbers of element', ({orders, result}) => {
        expect(ordersMedianValue(orders)).toBe(result)
    })
})

test('should count orders average total', () => {
    const actual = ordersAverageValue(ORDERS)
    expect(actual).toBe(698.96)
})
