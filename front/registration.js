import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import { Link, useHistory } from 'react-router-dom';

function Registration() {
  const { register, handleSubmit } = useForm();
  const [error, setError] = useState(null);
  const history = useHistory();

  const onSubmit = async (data) => {
    try {
      const response = await fetch('/register', {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
          'Content-Type': 'application/json'
        }
      });
      const responseData = await response.json();
      if (response.status === 200) {
        history.push('/welcome');
      } else {
        setError(responseData.message);
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
            name="name"
            ref={register({
              required: true,
              minLength: 8
            })}
          />
        </label>
        <label>
          Email
          <input
            name="email"
            ref={register({
              required: true,
              pattern: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i
            })}
          />
        </label>
        <label>
          Password
          <input
            type="password"
            name="password"
            ref={register({
              required: true,
              minLength: 8
            })}
          />
        </label>
        <button type="submit">Register</button>
      </form>
      <Link to="/">Login</Link>
    </div>
  );
}

export default Registration;