import React  from 'react';
import { Layout } from 'antd';
import LeftMenu from 'common/components/Layout/LeftMenu';

const { Header, Content, Footer, Sider } = Layout;

export default ({record}) => {
    return (
        <span>
            <a>Invite {record.subject}</a>
            <Divider type="vertical" />
            <a>Delete</a>
        </span>
    );
}