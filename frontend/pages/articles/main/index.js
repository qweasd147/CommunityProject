import React from 'react'
import { Provider } from 'mobx-react'
import { getSnapshot } from 'mobx-state-tree'
import initArticleStore from '../../../stores/article/MainArticleStore'
import MainArticles from '../../../container/article/MainArticles'

class ArticlesPage extends React.Component {
  static getInitialProps({ req }) {
    const isServer = !!req
    const articleStore = initArticleStore(isServer)
    return { initialState: getSnapshot(articleStore), isServer }
  }

  constructor(props) {
    super(props)
    this.state = {articleStore: initArticleStore(props.isServer, props.initialState)}
  }

  render() {
    return (
      <Provider articleStore = {this.state.articleStore}>
          <MainArticles />
      </Provider>
    )
  }
}

export default ArticlesPage;