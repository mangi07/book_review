import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import { Link, useNavigate } from 'react-router-dom';

function Registration() {
  const { register, handleSubmit } = useForm();
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const onSubmit = async (data) => {
    try {
      const response = await fetch('http://localhost:3000/auth/register', {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
          'Content-Type': 'application/json'
        }
      });
      const responseData = await response.json();
      if (response.status === 200) {
				// store JWT
				localStorage.setItem("token", responseData.token);
				const username = responseData.user;
        navigate('/welcome', { state: { username }});
      } else {
				setError(response);
      }
    } catch (err) {
      setError(err.message);
    }
  }

  return (
    <div>
      <h1>Registration</h1>
      {error && <p>{error}</p>}
      <form onSubmit={handleSubmit(onSubmit)}>
        <label>
          Name
          <input
            name="username"
            {...register("username",{
              required: true,
              minLength: 3
            })}
          />
        </label>
        <label>
          Password
          <input
            type="password"
            name="password"
            {...register("password",{
              required: true,
              minLength: 4
            })}
          />
        </label>
        <button type="submit">Register</button>
      </form>
      <Link to="/login">Login</Link>
    </div>
  );
}

export default Registration;
