import axios from "../../custom-axios/axios";

const BookService = {
    fetchBooks: () => {
        return axios.get("/books");
    },

    fetchAuthors: () => {
        return axios.get("/authors");
    },

    fetchCategories: () => {
        return axios.get("/categories");
    },

    getBooks: (id)=>{
        return axios.get(`/books/${id}`)
    },

    addBook :(name,category,author,availableCopies) => {
        return axios.post("/books/add",{
            "name":name,
            "category":category,
            "author":author,
            "availableCopies":availableCopies
        })
    },


    editBook: (id,name,category,author,availableCopies)=>{
        return axios.put(`/books/edit/${id}`, {
            "name":name,
            "category":category,
            "author":author,
            "availableCopies":availableCopies
        })
    },

    deleteBook : (id) => {
        return axios.delete(`/books/delete/${id}`);
    },


    markAsBook: (id) => {
        return axios.put(`/books/markAsTaken/${id}`);
    }
}

export default  BookService;