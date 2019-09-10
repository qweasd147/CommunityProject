import React from 'react'
import { inject, observer } from 'mobx-react'
import ReactMarkdown  from 'react-markdown';
import { useRouter, withRouter } from 'next/router'

const input = '# This is a header\n\nAnd this is a paragraph'

@inject('articleStore') @observer @withRouter
class MainArticleContainer extends React.Component {
  componentDidMount() {
    const { query } = this.props.router;
    
    this.props.articleStore.findOne(query.idx);
  }

  delete(){
    const { query } = this.props.router;

    this.props.articleStore.deleteOne(query.idx);
  }
  
  render() {
    return (
      <ReactMarkdown source={this.props.articleStore.one.contents} />
    );
  }
}

export default MainArticleContainer