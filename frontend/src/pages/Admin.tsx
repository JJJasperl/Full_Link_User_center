import { PageContainer } from '@ant-design/pro-components';
import React from 'react';

const Admin: React.FC = ({ children }) => {
  return (
    <PageContainer>
      {children}
    </PageContainer>
  );
};

export default Admin;
