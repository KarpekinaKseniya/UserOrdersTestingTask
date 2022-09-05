import React, {Component} from "react";
import {convertFullName, convertToDate} from "../actions/ConvertActions";
import {findAllUsers} from "../actions/BackendRequests";
import {CircularProgress, Link, Slide} from "@mui/material";
import Companies from "./Companies";

class Users extends Component {

    state = {
        users: [],
        open: false
    }

    componentDidMount() {
        this.getAllUsers()
    }

    async getAllUsers() {
        const body = await findAllUsers()
        this.setState({users: body})
    }

    findUserById(userId) {
        const user = this.state.users.find(val => userId === val.id)
        return convertFullName(user.first_name, user.last_name, user.gender)
    }

    visibility() {
        this.state.open === false ? this.setState({open: true}) : this.setState({open: false})
    }

    render() {
        const id = this.props.id
        const user = this.state.users.find(val => id === val.id)
        if (user === undefined) {
            return <CircularProgress color="inherit"/>
        } else
            return (
                <>
                    <Link href="#" onClick={this.visibility.bind(this)}>
                        {convertFullName(user.first_name, user.last_name, user.gender)}
                    </Link>
                    <Slide direction="right" in={this.state.open} mountOnEnter unmountOnExit>
                        <div className="user-details">
                            <p>Birthday: {convertToDate(user.birthday).split(' ')[0]}</p>
                            <p><img src={user.avatar} width="100px" alt={"Avatar " + user.id}/></p>
                            <Companies id={user.company_id}/>
                        </div>
                    </Slide>

                </>
            );
    }

}

export default Users;