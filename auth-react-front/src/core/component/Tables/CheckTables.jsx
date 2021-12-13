import React, { useEffect, useState } from 'react';
import { withStyles, makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Checkbox from '@material-ui/core/Checkbox';
import Grid from "@material-ui/core/Grid";
import Pagination from '@material-ui/lab/Pagination';
import { useTableStyles } from "@assets/style";
import Box from "@material-ui/core/Box";


const StyledTableCell = withStyles((theme) => ({
    body: {
        fontSize: 14,
    },
}))(TableCell);

const StyledTableRow = withStyles((theme) => ({
    root: {
        '&:nth-of-type(odd)': {
            backgroundColor: "#c8e1fa",
        },
    },
}))(TableRow);

function createData(check, name) {
    return { check, name };
}

const rows = [
    createData(true, 'Frozen yoghurt'),
    createData(false, 'Ice cream sandwich'),
    createData(true, 'Eclair'),
    createData(false, 'Cupcake'),
    createData(true, 'Gingerbread'),
];


export const CheckTables = ({ page = 1, pageSize = 10, pageCount = 10, columns = [] }) => {
    const classes = useTableStyles();
    const [currentPage, setCurrentPage] = useState(page);
    const [currentPageSize, setCurrentPageSize] = useState(pageSize);
    const [values, setValues] = useState([]);
    const handlePageChange = (event, value) => {
        setCurrentPage(value);
        /**
        doQuery(currentPageSize, value).then((executeList) => {
            setValues(executeList?.data ?? []);
        }).catch((err) => {

        });
         */
    };

    const handlePageSizeChange = (event) => {
        const { value } = event.target
        setCurrentPageSize(value);
        /**
        doQuery(value, currentPage).then((executeList) => {
            setValues(executeList?.data ?? []);
        }).catch((err) => {

        });
         */
    };
    useEffect(() => {
        /**
                doQuery(pageSize, page).then((executeList) => {
                    setValues(executeList?.data ?? []);
                }).catch((err) => {
        
                });
         */

    }, []);

    return (

        <>
            <Grid container justify="center" alignItems="center" item xs={12}>
                <TableContainer component={Paper} className={classes.tableMargin}>
                    <Table stickyHeader className={classes.smallTable} aria-label="customized table">
                        <TableBody>
                            {rows.map((row, index) => (
                                <StyledTableRow key={row.name}>
                                    <StyledTableCell component="th" scope="row">
                                        <Checkbox
                                            inputProps={{ 'aria-label': 'primary checkbox' }}
                                        />
                                    </StyledTableCell>
                                    <StyledTableCell component="th" scope="row">
                                        {row.name}
                                    </StyledTableCell>
                                </StyledTableRow>

                            ))}

                        </TableBody>
                    </Table>
                </TableContainer>
                <Grid container spacing={0}>
                    {pageCount != 0 ?
                        <Grid container justify="center" alignItems="center" item xs={12}>
                            <span>每页显示:</span>
                            <select
                                value={currentPageSize}
                                onChange={handlePageSizeChange}
                                name="currentPageSize"
                                className={classes.pageSelect}
                            >
                                <option value="10">10</option>
                                <option value="25">25</option>
                                <option value="50">50</option>
                                <option value="100">100</option>
                            </select>
                            <Pagination count={pageCount} page={currentPage} onChange={handlePageChange} variant="outlined" shape="rounded" size="small" showFirstButton showLastButton />
                        </Grid>
                        : ""}
                </Grid>
            </Grid>

        </>
    );

}