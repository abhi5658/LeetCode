/**
 * @param {string} s
 * @return {boolean}
 */
var isValid = function (s) {
    const len = s.length
    if (len % 2 != 0) {
        return false;
    }
    let stack = '';
    stack += s[0];
    for (let i = 1; i < len; i++) {
        if (opp(s[i], stack[0])) {
            stack = stack.slice(1);
        } else {
            stack = s[i] + stack;
        }
    }
    if (stack.length === 0) {
        return true;
    }
    return false;
};
var opp = function (br, vbr) {
    switch (br) {
        // case '(': return vbr === ')';
        case ')': return vbr === '(';
        // case '{': return vbr === '}';
        case '}': return vbr === '{';
        // case '[': return vbr === ']';
        case ']': return vbr === '[';
        default:
            return false;
    }
};