import React from 'react';
import App, { Container } from 'next/app'
import Layout from '../common/components/layout'

export default class MyApp extends App {
    constructor(props){
        super(props)
    }

    static async getInitialProps({ Component, router, ctx }) {
      
      let pageProps = {}
  
      if (Component.getInitialProps) {
        pageProps = await Component.getInitialProps(ctx)
      }
      return { pageProps }
    }
  
    render () {
        const { Component, pageProps } = this.props
        
        return (
          <Layout>
            <Component {...pageProps} />
          </Layout>
        );
    }
}