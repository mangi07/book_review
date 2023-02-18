import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import Reviews from './reviews.js'

function Search() {
  const { register, handleSubmit } = useForm();
  const [error, setError] = useState(null);
  const [data, setData] = useState(null);
  const [addMessage, setAddMessage] = useState(null);
  const [reviews, setBookReviews] = useState(null);
  const navigate = useNavigate();
	const { state } = useLocation();
	const { username } = state || {};

	// check for login before sending search request
	async function checkLogin() {
    try {
			const token = localStorage.token;
      const response = await fetch(`http://localhost:3000/userinfo`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Origin': 'null',
					'Authorization': `Bearer ${token}`
        }
      });
      if (response.status !== 200) {
				navigate("/login");
      }
	  } catch (err) {
      setError(err.message);
    }
	}

  const onSubmit = async (data) => {
		checkLogin();
    try {
			const isbn = data.isbn;
			const token = localStorage.token;
      const response = await fetch(`http://localhost:3000/search/${isbn}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Origin': 'null',
					'Authorization': `Bearer ${token}`
        }
      });
      const responseData = await response.json();
      if (response.status === 200) {
				console.log(responseData);
				// set state
				setData(responseData);
				setAddMessage("");
      } else {
				setError(response);
      }
    } catch (err) {
      setError(err.message);
    }
  }
  const onSubmitReview = async (data) => {
		checkLogin();
    try {
			const isbn = data.isbn;
			const token = localStorage.token;
      const response = await fetch(`http://localhost:3000/review/${isbn}`, {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
          'Content-Type': 'application/json',
          'Origin': 'null',
					'Authorization': `Bearer ${token}`
        }
      });
      const responseData = await response.json();
      if (response.status === 200) {
				console.log(responseData);
				// set state
				setData(responseData);
				setAddMessage("");
      } else {
				setError(response);
      }
    } catch (err) {
      setError(err.message);
    }
  }
  const onAddBook = async (data) => {
		checkLogin();
		const isbn = data.book.isbn
    try {
			const token = localStorage.token;
      const response = await fetch(`http://localhost:3000/add/${isbn}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Origin': 'null',
					'Authorization': `Bearer ${token}`
        }
      });
      const responseData = await response.json();
      if (response.status === 200) {
				console.log(responseData);
				setAddMessage("Book Added Successfully");
				data.book.inLibrary = true
      } else {
				setError(response);
      }
    } catch (err) {
      setError(err.message);
    }
  }

  const getBookReviews = async (data) => {
		checkLogin();
		const isbn = data.book.isbn
    try {
			const token = localStorage.token;
      const response = await fetch(`http://localhost:3000/list/reviews/${isbn}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Origin': 'null',
					'Authorization': `Bearer ${token}`
        }
      });
      const responseData = await response.json();
      if (response.status === 200) {
				console.log(responseData);
				setBookReviews(responseData.bookReviews);
				data.inLibrary = true
      } else {
				setError(response);
      }
    } catch (err) {
      setError(err.message);
    }
  }

  return (
    <div>
      <h1>Search</h1>
      {error && <p>{error}</p>}
      <form onSubmit={handleSubmit(onSubmit)}>
        <label>
          ISBN
          <input
            name="isbn"
            {...register("isbn",{
              required: true,
              minLength: 10
            })}
          />
        </label>
        <button type="submit">Search by ISBN</button>
      </form>
		  <Link to="/welcome" state={ {username:username} }>Back To Welcome Page</Link>
		  <div>
		    <h1>Search Results</h1>
		    { data &&
					<div>
					  <div>
					    <h2>Title:</h2> 
					    <p>{ data.book.title }</p>
					  </div>

					  <div>
					    <h2>Author:</h2> 
					    <p>{ data.book.author }</p>
					  </div>

					  <div>
					    <p>
                <img src={data.book.cover_image} alt="Cover" width="200" />
					    </p>
					  </div>

					  <div>
					    <h2>Description:</h2> 
					    <p>
								{ (data.book.description != null && data.book.description) ||
									"--"
								}
					    </p>
					  </div>

					  <div>
					    <h2>ISBN:</h2> 
					    <p>
					      { data.book.isbn }
					    </p>
					  </div>

					  <div>
					    <h2>Publication Date:</h2> 
					    <p>
					      { data.book.publication_date }
					    </p>
					  </div>

						{ ! data.inLibrary &&
							<div>
								<button onClick={() => onAddBook(data)}>
									Add Book To Library
								</button>
								<p>{ addMessage }</p>
							</div>
						}
						{ data.inLibrary &&
							<div>
								<form onSubmit={handleSubmit(onSubmitReview)}>
									<label>
										*Write Your Review Here
										<input
											name="review"
											{...register("review",{
												required: true,
												minLength: 1
											})}
										/>
									</label>
									<label>
								    *Rate This Book (1-5)
										<input
											name="rating"
							        type="number"
											{...register("rating",{
												required: true,
												valueAsNumber: true,
												validate: (value) => (value > 0 && value <= 5),
											})}
										/>
									</label>
									<button type="submit">Submit Review</button>
								</form>
							</div>
						}
						<div>
							<button onClick={() => getBookReviews(data)}>
							  List Book Reviews	
							</button>
						</div>
					  { reviews && <Reviews reviews={reviews} /> }
					</div>
				}
	    </div>
		  <p> Footer </p>
    </div>
  );
}

export default Search;
