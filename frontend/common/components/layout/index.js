import React, { useState } from 'react';
import CssBaseline from '@material-ui/core/CssBaseline';
import Container from '@material-ui/core/Container';

import Header from './Header';
import Left from './Left';
import MadeWithLove from './Copyright';

import useStyles from './Styles';


export default ({children}) => {
  
    const classes = useStyles();
    const [open, setOpen] = useState(true);
    const handleDrawerOpen = () => {
      setOpen(true);
    };
    const handleDrawerClose = () => {
      setOpen(false);
    };
  
    return (
      <div className = {classes.root}>
        <CssBaseline />
        <Header
          handleDrawerOpen = {handleDrawerOpen} open = {open}/>
        <Left 
          open = {open}
          handleDrawerClose = {handleDrawerClose}
        />
        <main className = {classes.content}>
          <div className = {classes.appBarSpacer} />
            <Container maxWidth="lg" className={classes.container}>
              {children}
            </Container>
          <MadeWithLove />
        </main>
      </div>
    );
}