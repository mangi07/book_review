// check for login before sending search request
export async function checkLogin() {
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
		return response.status === 200 
	} catch (err) {
		return false
	}
}
