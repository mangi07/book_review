import React, { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';

function Welcome() {
  const [error, setError] = useState(null);
	const { state } = useLocation();
	const { username } = state || {};
  const message = async () => {
    try {
			const token = localStorage.token;
      const response = await fetch('http://localhost:3000/', {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
					'Authorization': `Bearer ${token}`
        }
      });
      const responseData = await response.json();
      if (response.status === 200) {
				// store JWT
				localStorage.setItem("token", responseData.token);
      } else {
        setError(responseData.message);
      }
    } catch (err) {
      setError(err.message);
    }
  };

  return (
    <div>
      {error && <p>{error}</p>}
      <h1>Welcome, { username }!</h1>
      <p>{message}</p>
      <Link to="/search">Search</Link>
    </div>
  );
}

export default Welcome;

