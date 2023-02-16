import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
//import { Link/*, useNavigate*/ } from 'react-router-dom';

function Search() {
  const { register, handleSubmit } = useForm();
  const [error, setError] = useState(null);
  //const navigate = useNavigate();

  const onSubmit = async (data) => {
    try {
			const isbn = data.isbn;
			//const corsPrefix = "localhost:8080/"
			const token = localStorage.token;
      //const response = await fetch(`${corsPrefix}http://localhost:3000/search/${isbn}`, {
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
				// store JWT
				console.log(responseData);
        //navigate('/welcome');
      } else {
        //setError(responseData.message);
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
		  {/*<Link to="/login">Login</Link>*/}
    </div>
  );
}

export default Search;
