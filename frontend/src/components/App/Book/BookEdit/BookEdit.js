import React from "react";
import {useNavigate} from "react-router-dom";


const BookEdit = (props) => {
    const navigate = useNavigate();

    const [formData, updateFormData] = React.useState({
        name: "",
        category: "FANTASY",
        author: 1,
        availableCopies: 3
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        const {name, category, author, availableCopies} = formData;


        navigate('/books');


    }
}

export default BookEdit;