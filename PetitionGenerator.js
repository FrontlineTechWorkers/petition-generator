// read input parameters, should have 2 input parameters, filename and count
if (process.argv.length < 4) {
  console.log('Usage: node PetitionGenerator [input file name] [count]');
  process.exit(1);
}

var fs = require('fs'),
	filename = process.argv[2],
	count = process.argv[3];

//read the rawdata file as utf-8
fs.readFile(filename, 'utf8', function(err, data) {
  if (err) throw err;
  var names = data.replace(/\r/g, '').split('\n'),
  	firstname = [],
  	lastname = [];

  // convert the raw name list into an array of last name and first name respectively
  for(id in names) {
  	lastname.push(names[id][0]);
  	for(var i = 1; i < names[id].length; i++) {
  		firstname.push(names[id][i]);
  	}
  }

  // print a name from picking a char from last name and 2 chars from first name randomly, 3% chance to get only 1 char from first name
  while (count-- > 0) {
  	var name = lastname[Math.floor(Math.random()*lastname.length)] + firstname[Math.floor(Math.random()*firstname.length)];
  	if (Math.random() > 0.03)
  		name += firstname[Math.floor(Math.random()*firstname.length)];
  	console.log(name);
  }
});