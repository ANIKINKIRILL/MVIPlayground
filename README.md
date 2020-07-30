# MVIPlayground
MVI (Model-View-Intent) architecture is the kind of MVVM (Model-View-ViewModel), but with more features. The most big advantage of this architecture is if your app has many fragments in one activity, your ViewModel is getting massive and uncomprehendable. So MVI is ready to help!

<p>The app uses the following libraries / topics:</p>
<ul>
	<li>Kotlin</li>
	<li>Coroutines</li>
	<li>Retrofit2</li>
	<li>Glide</li>
	<li>ViewModels</li>
	<li>Repository pattern</li>
	<li>NetworkBoundResource (as recommend by architecture guide in google sample. See <a href="https://github.com/googlesamples/android-architecture-components/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/repository/NetworkBoundResource.kt" target="_blank">here</a>).</li>
</ul>
<br>
<p>The app does two things:</p>
<ol>
	<li>Get 'User' data from <a href="https://open-api.xyz/placeholder/user" target="_blank">open-api.xyz/placeholder/user</a>.</li>
	<li>Get a list of 'BlogPost' data from <a href="https://open-api.xyz/placeholder/blogs" target="_blank">open-api.xyz/placeholder/blogs</a>.</li>
</ol>
<br>
<p></p>
