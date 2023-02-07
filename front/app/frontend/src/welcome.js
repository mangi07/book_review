import React, { useState } from 'react';

function Welcome() {
  const [error, setError] = useState(null);
  const message = async () => {
    try {
			const token = localStorage.token;
      const response = await fetch('http://localhost:3000/', {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
					'Authorization': 'Bearer {token}'
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
      <h1>Welcome</h1>
      <p>{message}</p>
    </div>
  );
}

export default Welcome;

