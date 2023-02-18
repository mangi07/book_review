
function Book(props) {
	console.log(props)
	const book = props.book
  return (
		<div style={{border:'1px solid black'}}>
		  <p>Title: {book.title}</p>
		  <p>Author: {book.author}</p>
		  <img src={book.cover_image} alt={"Cover"}></img>
		  <p>Description: {book.description || "----"}</p>
		  <p>ISBN: {book.isbn}</p>
		  <p>Publication Date: {book.publication_date}</p>
		</div>
	)
}

export default Book;
