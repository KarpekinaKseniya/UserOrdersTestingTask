export const CURRENCY = [
    {code: 'USD', label: '$'},
    {code: 'UAH', label: '₴'},
    {code: 'BYN', label: '¤'},
    {code: 'EUR', label: '€'},
    {code: 'GBP', label: '£'},
    {code: 'JPY', label: '¥'},
    {code: 'CNY', label: '¥'},
    {code: 'RUB', label: '₽'},
    {code: 'GEL', label: '₾'},
    {code: 'PLN', label: 'Zł'},
    {code: 'AFN', label: '؋'},
    {code: 'KZT', label: '₸'},
    {code: 'TRY', label: '₺'},
    {code: 'AZN', label: '₼'},
    {code: 'KRW', label: '₩'},
    {code: 'THB', label: '฿'},
    {code: 'MNT', label: '₮'},
    {code: 'ILS', label: '₪'},
    {code: 'LAK', label: '₭'},
    {code: 'PHP', label: '₱'}
]

export const getAllCurrency = () => {
    return fetch('https://api.exchangerate-api.com/v6/latest')
        .then(cur => cur.json())
        .catch(error => alert(error))
}

export const countNewValue = (currency, toRate, fromRate, value) => {
    return (currency.rates[toRate] / currency.rates[fromRate]) * value
}