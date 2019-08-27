import React from 'react'
import { inject, observer } from 'mobx-react'
import Articles from '../../common/components/contents/article/Articles'

@inject('articleStore') @observer
class MainArticlesContainer extends React.Component {
  componentDidMount() {
    this.props.articleStore.findAll();
  }
  
  render() {
    return (
      <Articles articles={this.props.articleStore.articles}/>
    )
  }
}

//export default inject("articleStore")(observer(MainArticleContainer));
export default MainArticlesContainer