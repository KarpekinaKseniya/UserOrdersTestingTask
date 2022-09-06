export const convertToDate = (timestamp) => {
    const date = new Date(timestamp * 1000)
    const time = date.toLocaleTimeString()
    const day = date.getDate()
    const month = date.getMonth() + 1
    const year = date.getFullYear()
    return day + '/' + month + '/' + year + ' ' + time
}

export const convertAmount = (number) => {
    return (Math.round(number * 100) / 100).toFixed(2);
}

export const convertCardNumber = (cardNumber) => {
    const firstNumberCount = 2
    const lastNumberCount = 4
    const cardNumberLen = cardNumber.length
    const shieldedLen = cardNumberLen - firstNumberCount - lastNumberCount
    return cardNumber.substring(0, firstNumberCount) + "*".repeat(shieldedLen) + cardNumber.substring(cardNumberLen - lastNumberCount)
}

export const convertFullName = (firstName, lastName, gender) => {
    const appeal = 'Male' === gender ? 'Mr.' : 'Ms.'
    return appeal + ' ' + firstName + ' ' + lastName
}