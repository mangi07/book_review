import React, { useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import Library from './library.js';
import { checkLogin } from './checkLogin.js';

function Welcome() {
  const [error, setError] = useState(null);
  const [library, setLibrary] = useState(null);
	const { state } = useLocation();
	const navigate = useNavigate();
	const { username } = state || {};

	const getData = async () => {
		try {
			const loggedIn = await checkLogin()
			if ( ! loggedIn ) {
				navigate('/login')
			}
			const token = localStorage.token;
			const response = await fetch(`http://localhost:3000/library/${username}`, {
				method: 'GET',
				headers: {
					'Content-Type': 'application/json',
					'Origin': 'null',
					'Authorization': `Bearer ${token}`
				}
			});
			const responseData = await response.json();
			if (response.status === 200) {
				setLibrary(responseData)
			} else {
			}
		} catch (err) {
			setError(err.message);
		}
	};

  return (
    <div>
      {error && <p>{error}</p>}
      <h1>Welcome, { username }!</h1>
      <Link to="/search" state={ {username:username} }>
		    Search
		  </Link>
		  <h2>Your Library</h2>
			<div>
				<button onClick={() => getData()}>
					Load Library
				</button>
			</div>
		  {library && <Library library={library} />}
		  
    </div>
  );
}

export default Welcome;

