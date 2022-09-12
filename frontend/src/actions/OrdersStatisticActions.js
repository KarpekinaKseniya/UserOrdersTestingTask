export const ordersTotal = (orders) => {
    return orders.map(order => order.total).reduce((result, total) => result + total, 0)
}

export const ordersMedianValue = (orders) => {
    const values = orders.map(order => order.total)
    values.sort(function (a, b) {
        return a - b;
    })
    const half = Math.floor(values.length / 2)
    if (values.length % 2) {
        return values[half]
    }
    return (values[half - 1] + values[half]) / 2.0
}

export const ordersAverageValue = (orders) => {
    const count = orders.length
    const total = ordersTotal(orders)
    return total / count
}