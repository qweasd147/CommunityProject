import React from 'react';
import Link from '@material-ui/core/Link';
import Typography from '@material-ui/core/Typography';


export default () => {
    return (
        <Typography variant="body2" color="textSecondary" align="center">
            {'Built with love by the '}
            <Link color="inherit" href="https://material-ui.com/">
                Material-UI
            </Link>
            {' team.'}
        </Typography>
    );
};