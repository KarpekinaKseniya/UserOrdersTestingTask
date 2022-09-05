import React, {Component} from "react";
import {findAllCompanies} from "../actions/BackendRequests";
import DomainDisabledIcon from '@mui/icons-material/DomainDisabled';
import {Link, Popper, Typography} from "@mui/material";

class Companies extends Component {

    state = {
        companies: [],
        anchorEl: null
    }

    componentDidMount() {
        this.getAllCompanies()
    }

    async getAllCompanies() {
        const body = await findAllCompanies()
        this.setState({companies: body})
    }

    openInNewTab(url) {
        window.open(url, '_blank', 'noopener,noreferrer');
    }

    handlePopoverOpen(event) {
        this.setState({anchorEl: event.currentTarget});
    }

    handlePopoverClose() {
        setTimeout(() => {
            this.setState({anchorEl: null})
        }, 1500)
    }

    render() {
        const id = this.props.id
        const company = this.state.companies.find(val => id === val.id)
        if (company === undefined) {
            return <DomainDisabledIcon/>
        } else {
            return (
                <>
                    <Typography
                        aria-owns={Boolean(this.state.anchorEl) ? 'mouse-over-popover' : undefined}
                        aria-haspopup="true"
                        onMouseEnter={this.handlePopoverOpen.bind(this)}
                        onMouseLeave={this.handlePopoverClose.bind(this)}>Company:
                        <Link href="#" onClick={() => this.openInNewTab(company.url)}>{company.title}</Link>
                    </Typography>
                    <Popper
                        id="mouse-over-popover"
                        open={Boolean(this.state.anchorEl)}
                        anchorEl={this.state.anchorEl}
                        placement="right"
                        onClose={this.handlePopoverClose.bind(this)}>
                        <Link href="#" onClick={() => this.openInNewTab(company.url)}
                              sx={{border: 1, p: 1, bgcolor: 'background.paper'}}>{company.url}</Link>
                    </Popper>
                    <p>Industry: {company.industry}</p>
                </>
            )
        }
    }
}

export default Companies