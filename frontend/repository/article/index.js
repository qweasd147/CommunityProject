import axios from 'axios';

class ArticleRepository {
    URL = "/api/v1/type1/article";
    HOST = "http://localhost:8080";
  
    constructor(attr) {
      Object.assign(this, attr);
    }
  
    findAll(params) {
        return axios.get(`${this.HOST}${this.URL}`, { params });
    }
  
    findOne(articleIdx) {
        return axios.get(`${this.HOST}${this.URL}/${articleIdx}`);
    }

    createOne(params){
        return axios.post(`${this.HOST}${this.URL}`, { params });
    }

    modifyOne(articleIdx, params){
        return axios.put(`${this.HOST}${this.URL}/${articleIdx}`, { params });
    }

    deleteOne(articleIdx) {
        return axios.delete(`${this.HOST}${this.URL}/${articleIdx}`);
    }
  }

  // 싱글톤으로 리턴
  export default new ArticleRepository();