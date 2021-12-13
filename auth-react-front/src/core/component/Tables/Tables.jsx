import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Pagination from '@material-ui/lab/Pagination';
import Grid from "@material-ui/core/Grid";
import { useTableStyles } from "@assets/style";



const StyledTableCell = withStyles((theme) => ({
    head: {
        backgroundColor: "#2196f3",
        color: theme.palette.common.white,
    },
    body: {
        fontSize: 14,
    },

}))(TableCell);

const StyledTableRow = withStyles((theme) => ({
    root: {
        '&:nth-of-type(odd)': {
            backgroundColor: "#fff9e7",
        },
    },
}))(TableRow);




/**
 * 表格功能
 * 
 * @author aimee
 */
export const Tables = ({ page = 1, pageSize = 10, pageCount = 10, columns = [], doQuery }) => {
    const classes = useTableStyles();
    /** Input For Data Table Setting */
    const [currentPage, setCurrentPage] = useState(page);
    const [currentPageSize, setCurrentPageSize] = useState(pageSize);
    const [values, setValues] = useState([]);

    const handlePageChange = (event, value) => {
        setCurrentPage(value);
        doQuery(currentPageSize, value).then((executeList) => {
            setValues(executeList?.data ?? []);
        }).catch((err) => {

        });
    };

    const handlePageSizeChange = (event) => {
        const { value } = event.target
        setCurrentPageSize(value);
        doQuery(value, currentPage).then((executeList) => {
            setValues(executeList?.data ?? []);
        }).catch((err) => {

        });
    };

    useEffect(() => {
        doQuery(pageSize, page).then((executeList) => {
            setValues(executeList?.data ?? []);
        }).catch((err) => {

        });

    }, []);

    return (

        <>
            <TableContainer component={Paper} className={classes.tableMargin}>
                <Table stickyHeader className={classes.table} aria-label="customized table">
                    <TableHead>
                        <TableRow key='headerRow'>
                            {columns.map((column) => (
                                <StyledTableCell key={column.field} align={column.align}>{column.field}</StyledTableCell>
                            ))}
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {values.map((row, index) => (
                            <StyledTableRow key={'BodyRow-' + index}>
                                {columns.map((column) => (
                                    <StyledTableCell key={column.field} align={column.align}>{row[column.field]}</StyledTableCell>
                                ))}
                            </StyledTableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            {/* Paginator */}
            <Grid container spacing={0}>
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
            </Grid>

        </>
    );

}

Tables.propTypes = {
    page: PropTypes.number,
    pageSize: PropTypes.number,
    pageCount: PropTypes.number,
    columns: PropTypes.array,
    doQuery: PropTypes.func,
}