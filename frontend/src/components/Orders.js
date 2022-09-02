import React, {Component} from 'react';
import {styled} from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, {tableCellClasses} from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import {findAllOrders, findAllUsers} from "../actions/BackendRequests";
import {TablePagination} from "@mui/material";
import {convertAmount, convertCardNumber, convertFullName, convertToDate} from "../actions/ConvertActions";

const StyledTableCell = styled(TableCell)(({theme}) => ({
    [`&.${tableCellClasses.head}`]: {
        backgroundColor: theme.palette.primary.main,
        color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
    },
}));

const StyledTableRow = styled(TableRow)(({theme}) => ({
    '&:nth-of-type(odd)': {
        backgroundColor: theme.palette.action.hover,
    },
    '&:last-child td, &:last-child th': {
        border: 0,
    },
}));

class Orders extends Component {

    state = {
        orders: [],
        rowsPerPage: 5,
        page: 0,
        users: []
    }

    async componentDidMount() {
        await this.getAllOrders()
        await this.getAllUsers()
    }

    async getAllUsers() {
        const body = await findAllUsers()
        this.setState({users: body})
    }

    async getAllOrders() {
        const body = await findAllOrders()
        this.setState({orders: body})
    }

    findUserById(userId) {
        const user = this.state.users.find(val => userId === val.id)
        return convertFullName(user?.first_name, user?.last_name, user?.gender)
    }

    handleChangePage = (_event, newPage) => {
        this.setState({page: newPage});
    }

    handleChangeRowsPerPage = (event) => {
        this.setState({page: 0, rowsPerPage: parseInt(event.target.value, 10)})
    }

    render() {
        let {orders, rowsPerPage, page} = this.state
        return (
            <Paper sx={{width: '90%', mb: 2, ml: 11.5}}>
                <TableContainer>
                    <Table sx={{minWidth: 700}} aria-label="customized table">
                        <TableHead>
                            <TableRow>
                                <StyledTableCell>Transaction ID</StyledTableCell>
                                <StyledTableCell align="right">User Info</StyledTableCell>
                                <StyledTableCell align="right">Order Date</StyledTableCell>
                                <StyledTableCell align="right">Order Amount</StyledTableCell>
                                <StyledTableCell align="right">Card Number</StyledTableCell>
                                <StyledTableCell align="right">Card Type</StyledTableCell>
                                <StyledTableCell align="right">Location</StyledTableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {orders.slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage).map((order) => (
                                <StyledTableRow key={"order_" + order.id}>
                                    <StyledTableCell component="th" scope="row">
                                        {order.transaction_id}
                                    </StyledTableCell>
                                    <StyledTableCell align="right">
                                        <a href="#">{this.findUserById(order.user_id)}</a>
                                    </StyledTableCell>
                                    <StyledTableCell
                                        align="right">{convertToDate(order.created_at)}</StyledTableCell>
                                    <StyledTableCell align="right">{convertAmount(order.total)}$</StyledTableCell>
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
                    </Table>
                </TableContainer>
                <TablePagination
                    rowsPerPageOptions={[5, 10, 25, 50, {label: 'All', value: orders.length}]}
                    component="div"
                    count={orders.length}
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