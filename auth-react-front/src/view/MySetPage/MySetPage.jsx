import React from 'react';
import { TabsPage } from "@core/component/TabsPage";

const runSet = (
    <div>XXXXX</div>
)

const historySet = (
    <div>pppppp</div>
)
const titles = [{ title: "执行中", value: "1", child: runSet }, { title: "历史", value: "2", child: historySet }]

export const MySetPage = () => {

    return (
        <TabsPage titles={titles}></TabsPage>
    );


}