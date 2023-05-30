const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {endUsers: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/endUsers'}).done(response => {
			this.setState({endUsers: response.entity._embedded.endUsers});
		});
	}

	render() {
		return (
			<EndUserList endUsers={this.state.endUsers}/>
		)
	}
}

class EndUserList extends React.Component{
	render() {
		const endUsers = this.props.endUsers.map(endUser =>
			<EndUser key={endUser._links.self.href} endUser={endUser}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Description</th>
					</tr>
					{endUsers}
				</tbody>
			</table>
		)
	}
}

class EndUser extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.endUser.firstName}</td>
				<td>{this.props.endUser.lastName}</td>
				<td>{this.props.endUser.description}</td>
			</tr>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)