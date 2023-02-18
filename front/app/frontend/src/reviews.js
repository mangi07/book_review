function Reviews(props) {
	const reviews = props.reviews
  return (
		<div>
			{reviews.map( 
					(review) => {
						return (
					    <div style={{border:'1px solid black'}}>
							  <h2>Review</h2>
								<p>{review.review}</p>
							  <h2>Rating</h2>
								<p>{review.rating}</p>
							</div>
						)
					}
				)
			}
		</div>
	)
}

export default Reviews;
