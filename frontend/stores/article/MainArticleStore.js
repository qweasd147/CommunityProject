import { applySnapshot } from "mobx-state-tree"
import { Article, Articles } from './model';
  
let articles = null;
  
export default function initArticleStore(isServer, snapshot = null) {
  if (isServer) {
    articles = Articles.create();
  }

  if (articles == null) {
    articles = Articles.create();
  }

  if (snapshot) {
    applySnapshot(articles, snapshot);
  }
  return articles;
}
