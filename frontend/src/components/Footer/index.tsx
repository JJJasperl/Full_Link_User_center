import {GithubOutlined, LinkedinOutlined} from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import React from 'react';

const Footer: React.FC = () => {
  const defaultMessage = "Jie_Liu"
  const currentYear = new Date().getFullYear()
  return (
    <DefaultFooter
      // style={{
      //   background: 'none',
      // }}
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: 'linkedin',
          title: <LinkedinOutlined />,
          href: 'https://www.linkedin.com/in/jie-liu-0b8276267/',
          blankTarget: true,
        },
        {
          key: 'github',
          title: <><GithubOutlined />Jie_Liu GitHub</>,
          href: 'https://github.com/JJJasperl',
          blankTarget: true,
        },
      ]}
    />
  );
};

export default Footer;
