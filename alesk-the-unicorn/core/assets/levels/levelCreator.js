let blocks = [];

function createLevel() {
    
    // Create ground
    for (let i = -100; i < 100; i++) {
        blocks.push({X: i * 50, Y: 0});
    }

    // Create some really large stairs
    for (let i = 0; i < 1000; i++) {
        blocks.push({X: 50 * 50 + i * 50, Y: 6 * 50 + 50 * Math.round((i / 6))});
    }
}

createLevel();

const level = {
    blocks
};

var fs = require('fs');
fs.writeFile('level1.json', JSON.stringify(level), 'utf8', () => {console.log('Done.')});