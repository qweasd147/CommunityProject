import React from 'react';
import { Layout, Menu, Icon } from 'antd';
import Router from 'next/router';

const { Sider } = Layout;

const data = [
    {
        icon : "user"
        , router : "/"
        , text : "home"
    }
    , {
        icon : "video-camera"
        , router : "/articles/main"
        , text : "MainArticle"
    }
    , {
        icon : "upload"
        , router : "/articles/write"
        , text : "write"
    }
    , {
        icon : "user"
        , router : "/articles/show"
        , text : "show"
    }
];

const mapToComponents = (data) => {
    return data.map(({ router, icon, text }, i) => {
        return (
           <Menu.Item key={i+1} onClick={()=>Router.push(router)}>
                <Icon type={icon} />
                <span className="nav-text">{text}</span>
            </Menu.Item>
        );
    });
};

export default (props) => {
    return (
        <Sider
            breakpoint="lg"
            collapsedWidth="0"
            onBreakpoint={broken => {
            console.log(broken);
            }}
            onCollapse={(collapsed, type) => {
                console.log(collapsed, type);
            }}
        >
            <div className="logo" />
            <Menu theme="dark" mode="inline" defaultSelectedKeys={['4']}>
                {mapToComponents(data)}
            </Menu>
        </Sider>
    );
};
        