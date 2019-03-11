import React, { Component } from 'react'
import './TwitterPost.css';
import axios from 'axios'


class TwitterPost extends Component {

  constructor () {
    super()

    this.submitPost = this.submitPost.bind(this)
    this.handleNameChange = this.handleNameChange.bind(this);
    this.handleTexChange = this.handleTexChange.bind(this);
    this.handleSelectChange = this.handleSelectChange.bind(this);
    this.handleWordsChange = this.handleWordsChange.bind(this);

    this.state = {
      twitterPosts: [],
      filters: [],
      text:'',
      author:'',
      filter: 'Choose filter',
      words: 0,
    }

  }

  async componentWillMount() {
    try {
      const veggieFilters = await axios.get(`https://twitterfilterservice.azurewebsites.net/VeggieFilter`);
      this.setState({
        filters: veggieFilters.data.filters,
      })
    } catch (error) {
      console.error(error)
    }
  }

  handleNameChange(event) {
    this.setState({
      author: event.target.value
    });
  }

  handleTexChange(event) {
    this.setState({
      text: event.target.value
    });
  }

  handleSelectChange(event) {
    this.setState({
      filter: event.target.value
    });
  }

  handleWordsChange(event) {
    this.setState({
      words: event.target.value
    });
  }

  async censorMessage() {
    console.log(this.state.filter)
    return await axios.post(
      `https://twitterfilterservice.azurewebsites.net/VeggieFilter?filter=${this.state.filter}`,
      this.state.text,
      {
        headers: {
          "Content-Type": "text/plain"
        }
      }
    );
  }


  async submitPost(event) {
    event.preventDefault();

    const xmls=`<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
      <CountWords xmlns="http://tool/">
        <fullText xmlns="">${this.state.text}</fullText>
      </CountWords>
    </Body>
  </Envelope>`;

    axios.post(`http://wordcounterservice.azurewebsites.net:80/WordCounter?wsdl`,
    xmls,
    {headers:
    {
    'Content-Type': 'text/xml',
    SOAPAction: 'http://wordcounterservice.azurewebsites.net:80/WordCounter?wsdl'
    }
    }).then(async res=>{
      var xml = new DOMParser().parseFromString(res.data, "text/xml");
      const words = xml.getElementsByTagName("return")[0].childNodes[0].nodeValue;

      const censoredMessage = await this.censorMessage();

      this.state.twitterPosts.unshift({
      text: censoredMessage.data.filteredMessage,
      author: this.state.author,
      filter: this.state.filter,
      words: words
      });

      // reset form fields
      this.setState({
      text:'',
      author:'',
      filter: 'Choose filter'
      });

    }).catch(err=>{console.log(err)});

  }

  render() {
    return (
      <div className="content __wait">
        <h1>Twitter Post</h1>
        <h3>filter your thoughts</h3>
        <div className="_secondary">

            <form className="form" autoComplete="off">
              <select className="select" value={this.state.filter} onChange={this.handleSelectChange}>
                  <option  disabled>Choose filter</option>
                  {this.state.filters.map(filter => <option  key={filter} >{filter}</option>)}
              </select>
              <input id="name" placeholder="Your Name Goes Here..." value={this.state.author} onChange={this.handleNameChange}/>
              <textarea className="_twemoji_input" rows="7" value={this.state.text} onChange={this.handleTexChange}></textarea>
              <button onClick={this.submitPost}>Send</button>
            </form>

            <h3 id="message">
              <img className="emoji" alt="emojiImg" src="https://twemoji.maxcdn.com/36x36/1f64c.png"/>
              Show your message to the world
              <img className="emoji" alt="emojiImg" src="https://twemoji.maxcdn.com/36x36/1f30d.png"/>
            </h3>

            <ul className="messages">
              {this.state.twitterPosts.map((post,i) =>
                <li key={i}>
                  <h4>{post.author}</h4>
                  <p>{post.text}</p>
                  <h5 onChange={this.handleWordsChange}>Words: {post.words} </h5>
                </li>
              )}
            </ul>

        </div>
      </div>
    )
  }

}
export default TwitterPost
