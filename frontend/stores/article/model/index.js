import { types, flow, getParent } from "mobx-state-tree";
import articleRepository from '../../../repository/article';


const Article = types
  .model({
    idx : types.optional(types.number, -1)
    , subject : types.optional(types.string, '')
    , contents : types.optional(types.string, '')
    , hits : types.optional(types.number, 0)
    //, writer : types.string
    , loadState : types.optional(types.string, 'ready')
  })
  .actions((self) => ({
    fetch: flow(function* fetchData(boardIdx) {

        self.loadState = 'ready';

        try {
            const data = yield articleRepository.findOne(boardIdx).data;

            self = { ...data, loadState:'success' };
        } catch (error) {
            console.error(error)
            self.loadState = 'fail';
        }
    })
    , modifyOne: flow(function* fetchData(boardIdx, params) {

        self.loadState = 'ready';

        try {
            const data = yield articleRepository.modifyOne(boardIdx, params).data;
            //TODO : spread
            self = { ...data, loadState:'success' };
        } catch (error) {
            console.error(error)
            self.loadState = 'fail';
        }
    })
    , deleteOne: flow(function* fetchData(boardIdx, params) {

        self.loadState = 'ready';

        try {
            const data = yield articleRepository.deleteOne(boardIdx);

            destroy(self);

            self.loadState = 'success';
        } catch (error) {
            console.error(error)
            self.loadState = 'fail';
        }
    })
  }))


const Articles = types.model({
    list: types.array(Article)
    , isLast : false
    , count : 0
    , loadState : types.optional(types.string, 'ready')
    , one :  types.optional(Article, {})
}).actions((self) => ({
    findAll: flow(function* fetchData(params) {

        self.loadState = 'ready';
        try {
            const data = yield articleRepository.findAll(params);

            self.articles = data.data.listData;
            self.isLast = data.data.isLast;
            self.count = data.data.count;

            self.loadState = 'success';
        } catch (error) {
            console.error(error)
            self.loadState = 'fail';
        }
    })
    , findOne: flow(function* fetchData(articleIdx) {

        self.loadState = 'ready';
        try {
            const data = yield articleRepository.findOne(articleIdx);

            self.one = data.data;

            self.loadState = 'success';
        } catch (error) {
            console.error(error)
            self.loadState = 'fail';
        }
    })
  }))

export {
    Article
    , Articles
};
