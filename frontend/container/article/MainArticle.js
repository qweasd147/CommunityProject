import React from 'react'
import { inject, observer } from 'mobx-react'
import Article from '../../common/components/contents/article/Article'

//@inject('articleStore') @observer
class MainArticleContainer extends React.Component {
  componentDidMount() {
    this.props.articleStore.findAll();
  }
  
  render() {
    return (
      <Article articles={this.props.articleStore.articles}/>
    )
  }
}

export default inject("articleStore")(observer(MainArticleContainer));