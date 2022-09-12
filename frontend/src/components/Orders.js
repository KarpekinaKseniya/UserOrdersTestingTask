import React, {Component} from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import SearchIcon from '@mui/icons-material/Search';
import {findAllOrders, findAllUsers} from "../actions/BackendRequests";
import {
    IconButton,
    InputBase,
    MenuItem,
    Select,
    TableCell,
    TableFooter,
    TablePagination,
    TableSortLabel
} from "@mui/material";
import {convertAmount, convertCardNumber, convertToDate} from "../actions/ConvertActions";
import {StyledTableCell, StyledTableRow} from "../style/StyledTable"
import {ordersAverageValue, ordersMedianValue, ordersTotal} from "../actions/OrdersStatisticActions";
import Users from "./Users";
import {countNewValue, CURRENCY, getAllCurrency} from "../actions/CurrencyActions";

class Orders extends Component {

    state = {
        orders: [],
        rowsPerPage: 10,
        page: 0,
        users: [],
        orderBy: "",
        direction: 'asc',
        ordersFound: [],
        isFounded: false,
        currency: undefined,
        rate: 'USD',
        search: ''
    }

    async componentDidMount() {
        await this.getAllOrders()
        await this.getAllUsers()
        await this.findCurrency()
    }

    async getAllOrders() {
        const body = await findAllOrders()
        this.setState({orders: body})
    }

    async getAllUsers() {
        const body = await findAllUsers()
        this.setState({users: body})
    }

    async findCurrency() {
        const body = await getAllCurrency()
        this.setState({currency: body})
    }

    handleChangePage = (_event, newPage) => {
        this.setState({page: newPage});
    }

    handleChangeRowsPerPage = (event) => {
        this.setState({page: 0, rowsPerPage: parseInt(event.target.value, 10)})
    }

    findUserById(users, id) {
        const user = users.find(val => id === val.id)
        if (user !== undefined)
            return user
    }

    findOrdersByUserGender(orders, users, gender) {
        return orders.filter(order => {
            const user = this.findUserById(users, order.user_id)
            if (user !== undefined) {
                return user.gender === gender
            }
            return null
        })
    }

    findFirstAndLastName(id) {
        const user = this.findUserById(this.state.users, id)
        return user['first_name'] + ' ' + user['last_name']
    }

    descendingComparator(a, b, orderBy) {
        let first = b[orderBy]
        let second = a[orderBy]
        if (orderBy === "order_country") {
            first += " " + b['order_ip']
            second += " " + a['order_ip']
        } else if (orderBy === "user_id") {
            first = this.findFirstAndLastName(b["user_id"])
            second = this.findFirstAndLastName(a["user_id"])
        }
        if (first < second) {
            return -1;
        }
        if (first > second) {
            return 1;
        }
        return 0;
    }

    getComparator(direction, orderBy) {
        return direction === 'desc'
            ? (a, b) => this.descendingComparator(a, b, orderBy)
            : (a, b) => -this.descendingComparator(a, b, orderBy)
    }

    handleRequestSort = (_event, property) => {
        const isAsc = this.state.orderBy === property && this.state.direction === 'asc'
        this.setState({direction: (isAsc ? 'desc' : 'asc'), orderBy: property})
    };

    createSortHandler = (property) => (event) => {
        this.handleRequestSort(event, property)
    };

    containsIgnoreCase(string, contain) {
        const refer = (string + '').toLowerCase()
        const search = (contain + '').toLowerCase()
        return refer.includes(search)
    }

    containsFirstOrLastName(userId, value) {
        const user = this.findUserById(this.state.users, userId)
        return this.containsIgnoreCase(user.first_name, value) || this.containsIgnoreCase(user.last_name, value)
    }

    handleChange(event) {
        let value = event.target.value;
        const result = this.state.orders.filter(order =>
            this.containsIgnoreCase(order.transaction_id, value) ||
            this.containsIgnoreCase(order.total, value) ||
            this.containsIgnoreCase(order.card_type, value) ||
            this.containsIgnoreCase(order.order_country + ' (' + order.order_ip + ')', value) ||
            this.containsFirstOrLastName(order.user_id, value)
        )
        this.setState({ordersFound: result, isFounded: true, search: value})
    }

    changeCurrency(event) {
        const rateTo = event.target.value
        this.state.orders.forEach(order => order.total = countNewValue(this.state.currency, rateTo, this.state.rate, order.total))
        this.setState({rate: rateTo, search: '', ordersFound: this.state.orders})
    }

    render() {
        let {orders, rowsPerPage, page, direction, orderBy, users, ordersFound, isFounded, rate} = this.state
        let output = isFounded ? ordersFound : orders
        let ordersResult = output.sort(this.getComparator(direction, orderBy)).slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
        return (
            <Paper sx={{width: '90%', mb: 2, ml: 11.5}}>
                <TableContainer>
                    <Table sx={{minWidth: 700}} aria-label="customized table">
                        <TableHead>
                            <TableRow>
                                <TableCell>
                                    <Select
                                        sx={{m: 1, width: 200}}
                                        id="select-currency"
                                        value={rate}
                                        label="Choose currency"
                                        onChange={this.changeCurrency.bind(this)}>
                                        {CURRENCY.map((cur) => (
                                            <MenuItem value={cur.code} key={cur.code}>
                                                {cur.code} {cur.label}
                                            </MenuItem>
                                        ))}
                                    </Select>
                                </TableCell>
                                <TableCell colSpan="5">
                                    <InputBase
                                        fullWidth
                                        value={this.state.search}
                                        placeholder="Search Orders by Transaction ID|User(First and Last Name)|Amount|Card Type|Location"
                                        onChange={this.handleChange.bind(this)}
                                        inputProps={{'aria-label': 'search orders'}}/>
                                </TableCell>
                                <TableCell align="right">
                                    <IconButton type="button" sx={{p: '10px'}} aria-label="search" disabled>
                                        <SearchIcon/>
                                    </IconButton>
                                </TableCell>
                            </TableRow>
                            <TableRow>
                                <StyledTableCell
                                    key="transaction_id"
                                    sortDirection={orderBy === "transaction_id" ? direction : false}>
                                    <TableSortLabel
                                        active={orderBy === "transaction_id"}
                                        direction={orderBy === "transaction_id" ? direction : 'asc'}
                                        onClick={this.createSortHandler("transaction_id")}>
                                        Transaction ID
                                    </TableSortLabel>
                                </StyledTableCell>
                                <StyledTableCell
                                    key="user_id"
                                    align="right"
                                    sortDirection={orderBy === "user_id" ? direction : false}>
                                    <TableSortLabel
                                        active={orderBy === "user_id"}
                                        direction={orderBy === "user_id" ? direction : 'asc'}
                                        onClick={this.createSortHandler("user_id")}>
                                        User Info
                                    </TableSortLabel>
                                </StyledTableCell>
                                <StyledTableCell
                                    key="created_at"
                                    align="right"
                                    sortDirection={orderBy === "created_at" ? direction : false}>
                                    <TableSortLabel
                                        active={orderBy === "created_at"}
                                        direction={orderBy === "created_at" ? direction : 'asc'}
                                        onClick={this.createSortHandler("created_at")}>
                                        Order Date
                                    </TableSortLabel>
                                </StyledTableCell>
                                <StyledTableCell
                                    key="total"
                                    align="right"
                                    sortDirection={orderBy === "total" ? direction : false}>
                                    <TableSortLabel
                                        active={orderBy === "total"}
                                        direction={orderBy === "total" ? direction : 'asc'}
                                        onClick={this.createSortHandler("total")}>
                                        Order Amount
                                    </TableSortLabel>
                                </StyledTableCell>
                                <StyledTableCell align="right">Card Number</StyledTableCell>
                                <StyledTableCell
                                    key="card_type"
                                    align="right"
                                    sortDirection={orderBy === "card_type" ? direction : false}>
                                    <TableSortLabel
                                        active={orderBy === "card_type"}
                                        direction={orderBy === "card_type" ? direction : 'asc'}
                                        onClick={this.createSortHandler("card_type")}>
                                        Card Type
                                    </TableSortLabel>
                                </StyledTableCell>
                                <StyledTableCell
                                    key="order_country"
                                    align="right"
                                    sortDirection={orderBy === "order_country" ? direction : false}>
                                    <TableSortLabel
                                        active={orderBy === "order_country"}
                                        direction={orderBy === "order_country" ? direction : 'asc'}
                                        onClick={this.createSortHandler("order_country")}>
                                        Location
                                    </TableSortLabel>
                                </StyledTableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {output.length === 0 ?
                                <StyledTableRow>
                                    <StyledTableCell align="center" colSpan="7">Nothing Found</StyledTableCell>
                                </StyledTableRow>
                                : ordersResult.map((order) => (
                                    <StyledTableRow key={"order_" + order.id}>
                                        <StyledTableCell component="th" scope="row">
                                            {order.transaction_id}
                                        </StyledTableCell>
                                        <StyledTableCell align="right">
                                            <Users id={order.user_id} users={this.state.users}/>
                                        </StyledTableCell>
                                        <StyledTableCell
                                            align="right">{convertToDate(order.created_at)}</StyledTableCell>
                                        <StyledTableCell
                                            align="right">{convertAmount(order.total, rate)}</StyledTableCell>
                                        <StyledTableCell align="right">
                                            {convertCardNumber(order.card_number)}
                                        </StyledTableCell>
                                        <StyledTableCell align="right">{order.card_type}</StyledTableCell>
                                        <StyledTableCell
                                            align="right">{order.order_country} ({order.order_ip})
                                        </StyledTableCell>
                                    </StyledTableRow>
                                ))}
                        </TableBody>
                        <TableFooter>
                            <TableRow>
                                <TableCell>Orders Count</TableCell>
                                <TableCell align="right">Orders Total</TableCell>
                                <TableCell align="right">Median Value</TableCell>
                                <TableCell align="right">Average Check</TableCell>
                                <TableCell align="right">Average Check (Female)</TableCell>
                                <TableCell align="right" colSpan="2">Average Check (Male)</TableCell>
                            </TableRow>
                            <TableRow>
                                <TableCell>{rowsPerPage}</TableCell>
                                <TableCell
                                    align="right">{convertAmount(ordersTotal(ordersResult), rate)}</TableCell>
                                <TableCell
                                    align="right">{convertAmount(ordersMedianValue(ordersResult), rate)}</TableCell>
                                <TableCell
                                    align="right">{convertAmount(ordersAverageValue(ordersResult), rate)}</TableCell>
                                <TableCell align="right">
                                    {convertAmount(ordersAverageValue(this.findOrdersByUserGender(ordersResult, users, 'Female')), rate)}
                                </TableCell>
                                <TableCell align="right" colSpan="2">
                                    {convertAmount(ordersAverageValue(this.findOrdersByUserGender(ordersResult, users, 'Male')), rate)}
                                </TableCell>
                            </TableRow>
                        </TableFooter>
                    </Table>
                </TableContainer>
                <TablePagination
                    rowsPerPageOptions={[5, 10, 25, 50, {label: 'All', value: output.length}]}
                    component="div"
                    count={output.length}
                    rowsPerPage={rowsPerPage}
                    page={page}
                    onPageChange={this.handleChangePage}
                    onRowsPerPageChange={this.handleChangeRowsPerPage}
                />
            </Paper>
        );
    }
}

export default Orders;