import './App.css';
import {Component} from "react";
import Header from "../Header/header";
import {BrowserRouter as Router, Route, Routes,useNavigate} from "react-router-dom";
import BookService from "../repository/BookRepository";
import Categories from "../Category/categories";
import BookAdd from "./Book/BookAdd/BookAdd";


class App extends Component{

  constructor(props) {
    super(props);

    this.state={
      books: [],
      authors: [],
      categories: [],
      countries: [],
      selectedBook: {}

    }
  }

  render(){

    return(
        <Router>
            <Header/>
            <main>
                <Routes>
                    Route path={"/categories"} exact render={() =>
                    <Categories categories={this.state.categories}/>}/>
                    <Route path={"/books/add"} exact render={() =>
                        <BookAdd categories={this.state.categories}
                                    authors={this.state.authors}
                                    onAddBook={this.addBook}/>}/>



                </Routes>


            </main>

        </Router>

    )
  }


    loadBooks = () => {
        BookService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    loadAuthors= () => {
        BookService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }

    loadCategories= () => {
        BookService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }



    getBook= (id)=>{
        BookService.getBooks(id)
            .then((data)=>{
                this.setState({
                    selectedBook:data.data
                });
            })
    }


    addBook= (name,category,author,availableCopies)=> {
        BookService.addBook(name,category,author,availableCopies)
            .then(()=> {
                this.loadBooks();
            });
    }


    editBook=(id,name,category,author,availableCopies)=>{
        BookService.editBook(id,name,category,author,availableCopies)
            .then(()=>{
                this.loadBooks()
            })
    }


    deleteBook= (id)=>{
        BookService.deleteBook(id)
            .then( ()=> {
                this.loadBooks();
            });
        window.location.reload();
    }

    markAsTakenBook= (id) => {
        BookService.markAsBook(id)
            .then(()=>{
                this.loadBooks();
            })
        window.location.reload();
    }


    componentDidMount() {
        this.loadBooks();
        this.loadAuthors();
        this.loadCategories();
    }


}



export default App;