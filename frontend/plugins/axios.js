export default function ({ $axios, redirect, store }) {
  $axios.onRequest(config => {
  	config.headers.Authorization = store.state.token
  	$axios.setToken(store.state.token)
    console.log('Making request to ' + config.url)
  })

  $axios.onError(error => {
    const code = parseInt(error.response && error.response.status)
    if (code === 400) {
      //redirect('/400')
    }
  })
}