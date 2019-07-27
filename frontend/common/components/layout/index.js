import React  from 'react';
import { Layout } from 'antd';
import LeftMenu from 'common/components/Layout/LeftMenu';

const { Header, Content, Footer, Sider } = Layout;

export default ({children}) => {
    return (
      <Layout>
        <LeftMenu/>
        <Layout>
          <Header style={{ background: '#fff', padding: 0 }} />
          <Content style={{ margin: '24px 16px 0' }}>
            <div style={{ padding: 24, background: '#fff', minHeight: 360 }}>
              {children}
            </div>
          </Content>
          <Footer style={{ textAlign: 'center' }}>Ant Design ©2018 Created by Ant UED</Footer>
        </Layout>
      </Layout>
    );
}