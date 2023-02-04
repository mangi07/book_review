import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';

function Login() {
  const { register, handleSubmit } = useForm();
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const onSubmit = async (data) => {
    try {
      const response = await fetch('http://localhost:3000/auth/login', {
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
        navigate('/welcome');
      } else {
        setError(responseData.message);
      }
    } catch (err) {
      setError(err.message);
    }
  }

  return (
    <div>
      <h1>Login</h1>
      {error && <p>{error}</p>}
      <form onSubmit={handleSubmit(onSubmit)}>
        <label>
          Name
          <input
            name="username"
            {...register("username",{
              required: true,
              minLength: 8
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
              minLength: 8
            })}
          />
        </label>
        <button type="submit">Login</button>
      </form>
    </div>
  );
}

export default Login;
