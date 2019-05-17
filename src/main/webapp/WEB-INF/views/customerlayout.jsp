<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/index.css">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
</head>

<body>
	<header> <nav
		class="navbar navbar-expand-lg navbar-light bg-light"> <a
		class="navbar-brand" href="#">Navbar</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="#">Home
					<span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> Dropdown </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="#">Action</a> <a
						class="dropdown-item" href="#">Another action</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="#">Something else here</a>
				</div></li>
			<li class="nav-item"><a class="nav-link disabled" href="#"
				tabindex="-1" aria-disabled="true">Disabled</a></li>
		</ul>
		<form class="form-inline my-2 my-lg-0">
			<input class="form-control mr-sm-2" type="search"
				placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>
	</div>
	</nav> </header>

	<div class="container">
		<jsp:include page="${param.view}" />
	</div>

	<footer> <section class="footers bg-light pt-5 pb-3">
	<div class="container pt-5">
		<div class="row">
			<div class="col-xs-12 col-sm-6 col-md-4 footers-one">
				<div class="footers-logo">
					<img src="http://velikorodnov.com/html/autotrader/images/logo.png"
						alt="Logo" style="width: 120px;">
				</div>
				<div class="footers-info mt-3">
					<p>Cras sociis natoque penatibus et magnis Lorem Ipsum tells
						about the compmany right now the best.</p>
				</div>
				<div class="social-icons">
					<a href="https://www.facebook.com/"><i id="social-fb"
						class="fa fa-facebook-square fa-2x social"></i></a> <a
						href="https://twitter.com/"><i id="social-tw"
						class="fa fa-twitter-square fa-2x social"></i></a> <a
						href="https://plus.google.com/"><i id="social-gp"
						class="fa fa-google-plus-square fa-2x social"></i></a> <a
						href="mailto:bootsnipp@gmail.com"><i id="social-em"
						class="fa fa-envelope-square fa-2x social"></i></a>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-2 footers-two">
				<h5>Essentials</h5>
				<ul class="list-unstyled">
					<li><a href="maintenance.html">Search</a></li>
					<li><a href="contact.html">Sell your Car</a></li>
					<li><a href="about.html">Advertise with us</a></li>
					<li><a href="about.html">Dealers Portal</a></li>
					<li><a href="about.html">Post Requirements</a></li>
				</ul>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-2 footers-three">
				<h5>Information</h5>
				<ul class="list-unstyled">
					<li><a href="maintenance.html">Register Now</a></li>
					<li><a href="contact.html">Advice</a></li>
					<li><a href="about.html">Videos</a></li>
					<li><a href="about.html">Blog</a></li>
					<li><a href="about.html">Services</a></li>
				</ul>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-2 footers-four">
				<h5>Explore</h5>
				<ul class="list-unstyled">
					<li><a href="maintenance.html">News</a></li>
					<li><a href="contact.html">Sitemap</a></li>
					<li><a href="about.html">Testimonials</a></li>
					<li><a href="about.html">Feedbacks</a></li>
					<li><a href="about.html">User Agreement</a></li>
				</ul>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-2 footers-five">
				<h5>Company</h5>
				<ul class="list-unstyled">
					<li><a href="maintenance.html">Career</a></li>
					<li><a href="about.html">For Parters</a></li>
					<li><a href="about.html">Terms</a></li>
					<li><a href="about.html">Policy</a></li>
					<li><a href="contact.html">Contact Us</a></li>
				</ul>
			</div>

		</div>
	</div>
	</section> <section class="disclaimer bg-light border">
	<div class="container">
		<div class="row ">
			<div class="col-md-12 py-2">
				<small> Disclaimer: Element Limited is only an intermediary
					offering its platform to facilitate the transactions between Seller
					and Customer/Buyer/User and is not and cannot be a party to or
					control in any manner any transactions between the Seller and the
					Customer/Buyer/User. All the offers and discounts on this Website
					have been extended by various Builder(s)/Developer(s) who have
					advertised their products. Element is only communicating the offers
					and not selling or rendering any of those products or services. It
					neither warrants nor is it making any representations with respect
					to offer(s) made on the site. Element Limited shall neither be
					responsible nor liable to mediate or resolve any disputes or
					disagreements between the Customer/Buyer/User and the Seller and
					both Seller and Customer/Buyer/User shall settle all such disputes
					without involving Element Limited in any manner. </small>
			</div>
		</div>
	</div>
	</section> <section class="copyright border">
	<div class="container">
		<div class="row text-center">
			<div class="col-md-12 pt-3">
				<p class="text-muted">© 2018 xyz Software Pvt. Ltd.</p>
			</div>
		</div>
	</div>
	</section> </footer>

</body>
</html>