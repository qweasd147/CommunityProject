import { types, applySnapshot } from "mobx-state-tree"
import { Article, Articles } from './model';
  
let articles = null;
let article = null;
  
export default function initArticleStore(isServer, snapshot = null) {
  if (isServer) {
    articles = Articles.create();
    article = Article.create();
  }

  if (articles == null) {
    articles = Articles.create();
  }

  if (article == null) {
    article = Article.create();
  }

  if (snapshot) {
    applySnapshot(articles, snapshot);
  }
  return articles;
}
