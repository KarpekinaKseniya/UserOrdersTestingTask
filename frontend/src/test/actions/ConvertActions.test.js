import {convertAmount, convertCardNumber, convertFullName, convertToDate} from "../../actions/ConvertActions";

test('should convert timestamp to date-time', () => {
    expect(convertToDate(1543325996)).toEqual('27/11/2018 4:39:56 PM')
})

describe('Convert to float with fixed 2', () => {
    it.each`
    number     | rate         | result
    ${722.308} | ${'BYN'}     | ${'722.31¤'}
    ${14.5}    | ${undefined} | ${'14.50$'}
    ${NaN}     | ${'RUB'}     | ${'NaN'} 
    `
    ('Expecting $result when convert $number with $rate', ({number, rate, result}) => {
        expect(convertAmount(number, rate)).toBe(result)
    })
})

test('should convert card number', () => {
    const cardNumber = '4917751745461584'
    const actual = convertCardNumber(cardNumber)
    expect(actual).toBe('49**********1584')
})

describe('Get Full Name', () => {
    it.each`
    firstName  | lastName    |  gender        | result
    ${'John'}  | ${'Snow'}   | ${'Male'}      | ${'Mr. John Snow'} 
    ${'Marya'} | ${'Cooper'} | ${'Female'}    | ${'Ms. Marya Cooper'} 
    ${'Sam'}   | ${'Brown'}  | ${'Incorrect'} | ${'Ms. Sam Brown'}`
    ('Set $firstName, $lastName and $gender. Expecting $result',
        ({firstName, lastName, gender, result}) => {
            expect(convertFullName(firstName, lastName, gender)).toEqual(result)
        })
})